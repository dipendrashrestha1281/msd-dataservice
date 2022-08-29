
 
package com.bah.msd.mcc.repository;

import org.springframework.data.repository.CrudRepository;

import com.bah.msd.mcc.domain.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

}
