package org.sprintpay.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sprintpay.client.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
