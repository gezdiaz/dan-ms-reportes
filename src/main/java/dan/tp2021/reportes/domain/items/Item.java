package dan.tp2021.reportes.domain.items;

public abstract class Item {

    protected Integer id;

    public Item() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
