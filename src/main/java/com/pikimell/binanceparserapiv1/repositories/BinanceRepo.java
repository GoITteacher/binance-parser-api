package com.pikimell.binanceparserapiv1.repositories;

import com.pikimell.binanceparserapiv1.model.SymbolItem;
import org.springframework.data.repository.CrudRepository;

public interface BinanceRepo extends CrudRepository<SymbolItem, Long> {

}
