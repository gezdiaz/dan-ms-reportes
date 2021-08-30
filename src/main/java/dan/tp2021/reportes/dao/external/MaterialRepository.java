package dan.tp2021.reportes.dao.external;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dan.tp2021.reportes.domain.external.material.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {
}
