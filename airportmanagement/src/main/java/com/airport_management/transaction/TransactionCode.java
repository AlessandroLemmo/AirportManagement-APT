package com.airport_management.transaction;

import com.airport_management.repository.mongo.factory.RepositoryMongo;
import com.mongodb.Function;


@FunctionalInterface
public interface TransactionCode<T> extends Function<RepositoryMongo, T> { 
	
}
