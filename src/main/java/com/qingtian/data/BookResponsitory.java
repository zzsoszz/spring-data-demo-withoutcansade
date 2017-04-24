package com.qingtian.data;

import org.springframework.data.repository.CrudRepository;

public interface BookResponsitory   extends CrudRepository<Book, Long> {
	
}
