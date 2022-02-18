package com.coweco.server.services;

import com.coweco.server.models.Good;
import com.coweco.server.models.SessionAvito;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface MainService {

	public void saveGood(Good good);
	public List<Good> getAllGoods();

	public void saveSessionAvito(SessionAvito sessionAvito);
	public List<SessionAvito> getAllSessionAvitos();

}
