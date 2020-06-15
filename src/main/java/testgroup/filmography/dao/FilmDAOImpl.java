package testgroup.filmography.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import testgroup.filmography.model.Film;

import java.util.List;


@Repository
public class FilmDAOImpl implements FilmDAO{
    private SessionFactory sessionFactory;
    /*public static final AtomicInteger AUTO_ID = new AtomicInteger(1);
    public static Map<Integer, Film> films = new HashMap<>();*/

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Film> allFilms(int page) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Film").setFirstResult(10 * (page - 1)).setMaxResults(10).list();
    }

    @Override
    public void add(Film film) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(film);

    }

    @Override
    public void edit(Film film) {
        Session session = sessionFactory.getCurrentSession();
        session.update(film);

    }

    @Override
    public void delete(Film film) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(film);

    }

    @Override
    public Film getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Film.class, id);
    }



    @Override
    public int filmsCount(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count(id) from Film", Number.class).getSingleResult().intValue();
    }
}
