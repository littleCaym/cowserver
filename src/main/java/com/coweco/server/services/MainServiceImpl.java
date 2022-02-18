package com.coweco.server.services;

import com.coweco.server.models.Good;
import com.coweco.server.models.SessionAvito;
import com.coweco.server.repositories.GoodsRepository;
import com.coweco.server.repositories.SessionsRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainServiceImpl implements MainService {

	@Autowired
	GoodsRepository goodsRepository;
	@Autowired
	SessionsRepository sessionsRepository;

	@Override
	public void saveGood(Good good) {
		goodsRepository.save(good);
	}

	@Override
	public List<Good> getAllGoods() {
		return goodsRepository.findAll();
	}

	@Override
	public List<Good> getGoodsForLastSession() {
		return sessionsRepository.findFirstByOrderByIdDesc().getGoodSet().stream()
				.sorted(Comparator.comparing(Good::getId)).collect(Collectors.toList());
	}

	@Override
	public void saveSessionAvito(SessionAvito sessionAvito) {
		sessionsRepository.save(sessionAvito);
	}

//	@Override
//	public List<SessionAvito> getAllSessionAvitos() {
//		return sessionsRepository.findAll();
//	}

	@Override
	public SessionAvito getLastSessionAvito() {
		return sessionsRepository.findFirstByOrderByIdDesc();
	}


}
