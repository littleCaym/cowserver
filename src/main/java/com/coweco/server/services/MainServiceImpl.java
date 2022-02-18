package com.coweco.server.services;

import com.coweco.server.models.Good;
import com.coweco.server.models.SessionAvito;
import com.coweco.server.repositories.GoodsRepository;
import com.coweco.server.repositories.SessionsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * todo Document type GoodsServiceImpl
 */
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
	public void saveSessionAvito(SessionAvito sessionAvito) {
		sessionsRepository.save(sessionAvito);
	}

	@Override
	public List<SessionAvito> getAllSessionAvitos() {
		return sessionsRepository.findAll();
	}

}
