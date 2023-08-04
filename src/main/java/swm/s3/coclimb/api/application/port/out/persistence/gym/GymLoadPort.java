package swm.s3.coclimb.api.application.port.out.persistence.gym;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import swm.s3.coclimb.domain.gym.Gym;

import java.util.List;
import java.util.Optional;

public interface GymLoadPort {
    boolean existsByName(String name);

    Optional<Gym> findByName(String name);

    List<Gym> findAll();

    Page<Gym> findPage(PageRequest pageRequest);
}