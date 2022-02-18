package com.coweco.server.tasks;

import com.coweco.server.models.Good;
import com.coweco.server.models.SessionAvito;
import com.coweco.server.services.MainService;
import java.io.IOException;
import java.sql.Timestamp;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MainTask {

	//avito
	private static final String SEARCH_KOMBIKORM_FOR_KRS_MOSKVA_AND_MO = "https://www.avito.ru/moskva_i_mo/tovary_dlya_zhivotnyh?q=%D0%BA%D0%BE%D0%BC%D0%B1%D0%B8%D0%BA%D0%BE%D1%80%D0%BC+%D0%B4%D0%BB%D1%8F+%D0%BA%D1%80%D1%81";

	@Autowired
	MainService mainService;

	//https://www.baeldung.com/spring-scheduled-tasks
	//https://www.baeldung.com/cron-expressions
	//<minute> <hour> <day-of-month> <month> <day-of-week> <command>


	//TODO:	@Scheduled(cron = "* 0 9/6 * * *")
	@Scheduled(initialDelay = 1000, fixedDelay=Long.MAX_VALUE)
	public void parsingAvito(){
		try {
			Document doc = Jsoup.connect(SEARCH_KOMBIKORM_FOR_KRS_MOSKVA_AND_MO)
					.userAgent("Mozilla")
					.timeout(5000) //TODO: check!
					.get();

			//Время запроса
			SessionAvito sessionAvito = new SessionAvito();
			sessionAvito.setTimestamp(new Timestamp(System.currentTimeMillis()));
			mainService.saveSessionAvito(sessionAvito);
			sessionAvito = mainService.getLastSessionAvito();

			//HTML Parsing
			String strHtml = doc.outerHtml();
			doc = Parser.parse(strHtml,SEARCH_KOMBIKORM_FOR_KRS_MOSKVA_AND_MO);
			Elements goods = doc.getElementsByClass("iva-item-content-rejJg");

			for (Element e : goods) {

				Good good = new Good();

				//Название продукта(Title)
				good.setTitle(e.
						select("div[class=iva-item-titleStep-pdebR]").text());
				System.out.println(good.getTitle());

				//Название бренда
				good.setBrand(e.
						select("div[class=style-title-_wK5H text-text-LurtD text-size-s-BxGpL]").text());

				//Рейтинг продавца
				String strBrandRating = e.
						select("div[class=SellerRating-scoreAndStars-hiWti]").text();
				if (!strBrandRating.equals("")){
					strBrandRating = strBrandRating.replace(',','.');
					good.setBrand_rating(Float.parseFloat(strBrandRating));
				}
				else{
					good.setBrand_rating(0);
				}

				//Количество отзывов
				String strReviewsNum = e.select("div[class=margin-left:8px;max-width:126px]").text();
				if (!strReviewsNum.equals("")){
					strReviewsNum = strReviewsNum.substring(
							0, strBrandRating.indexOf(" ")-1
					);
					good.setReviews_num(Integer.parseInt(strReviewsNum));
				}
				else{
					good.setReviews_num(0);
				}

				//Цена продукта
				//Цена может быть не указана или в формате типа 500 р
				String st = e.select("div[class=iva-item-priceStep-uq2CQ]").text();
				st = st.substring(0, st.length()-2);
				try{
					good.setPrice(Float.parseFloat(st));
				} catch (Exception exception){
					good.setPrice(Float.NaN);
				}

				//Описание продукта
				good.setDescription(e.
						select("div[class=iva-item-descriptionStep-C0ty1]").text());

				//Локация
				good.setLocation(e.
						select("div[class=geo-root-zPwRk iva-item-geo-_Owyg]").text());

				//Время загрузки объявления
				good.setDate_upload(e.
						select("div[class=iva-item-dateInfoStep-_acjp]").text());

				//Ссылка
				//Добавим вначале https://www.avito.ru/
				good.setLink("https://www.avito.ru/"+
						e.getElementsByTag("a").first().attr("href")
				);

				good.setSessionAvito(sessionAvito);
				//Сохранение Продуктов в БД
				mainService.saveGood(good);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
