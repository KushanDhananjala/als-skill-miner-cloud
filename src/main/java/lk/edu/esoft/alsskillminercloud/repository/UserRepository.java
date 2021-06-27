package lk.edu.esoft.alsskillminercloud.repository;

import lk.edu.esoft.alsskillminercloud.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT u.name, u.display_name, u.email, u.password, u.age, u.join_date, u.location, \n" +
            "u.about, u.reputation, u.facebook_url, u.github_url, u.profile_image_url, u.points, u.badge_id \n" +
            "FROM user u \n" +
            "ORDER BY (u.points) DESC", nativeQuery = true)
    ArrayList<Object[]> getTopFiveUsers(Pageable pageable);

    @Query(value = "SELECT u.points \n" +
            "FROM User u \n" +
            " WHERE lower(u.name)=lower(:userName)")
    long getUserPoints(@Param("userName") String userName);

    @Modifying
    @Query(value = "UPDATE User u SET u.points=:points \n" +
            "WHERE lower(u.name)=lower(:userName)")
    void updateUserPoints(@Param("userName") String userName, @Param("points") long points);

    @Modifying
    @Query(value = "UPDATE User u SET u.badgeID=:badgeID \n" +
            "WHERE lower(u.name)=lower(:userName)", nativeQuery = true)
    void updateUserBadge(@Param("userName") String userName, @Param("badgeID") int badgeID);

}
