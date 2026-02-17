
package com.SmartAgriculture.Cropp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SmartAgriculture.Cropp.model.FarmerProfile;
import com.SmartAgriculture.Cropp.model.User;

@Repository
public interface FarmerProfileRepository extends JpaRepository<FarmerProfile, Long> {

    boolean existsByUser(User user);

    boolean existsByContactNumber(String contactNumber);

    Optional<FarmerProfile> findByUser(User user);

    @Modifying
    @Query("DELETE FROM FarmerProfile fp WHERE fp.user = :user")
    void deleteByUser(@Param("user") User user);

}