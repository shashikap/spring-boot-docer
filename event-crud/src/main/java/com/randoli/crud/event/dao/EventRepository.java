package com.randoli.crud.event.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.randoli.crud.event.dao.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

}
