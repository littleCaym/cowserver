package com.coweco.server.services;

import com.coweco.server.models.Good;
import com.coweco.server.models.SessionAvito;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface MainService {

	void saveGood(Good good);
	List<Good> getAllGoods();
	List<Good> getGoodsForLastSession();

	void saveSessionAvito(SessionAvito sessionAvito);
//	List<SessionAvito> getAllSessionAvitos();
	SessionAvito getLastSessionAvito();

}
