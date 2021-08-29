package dan.tp2021.reportes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dan.tp2021.reportes.domain.items.ItemMaterial;

@Repository
public interface ItemMaterialRepository extends JpaRepository<ItemMaterial, Integer> {
}
