package com.CourseWorkRusut.DAO.Impl;

import com.CourseWorkRusut.DAO.LibraryDAO;
import com.CourseWorkRusut.DTO.LibraryDTO;
import com.CourseWorkRusut.model.Library;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LibraryDAOImpl implements LibraryDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public LibraryDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Library getLibraryById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Library.class,id);
    }

    @Override
    public void updateLibrary(Library library) {
        Session session = sessionFactory.getCurrentSession();
        session.update(library);
    }

    @Override
    public List<LibraryDTO> getAllLibrary(String offset) {

        Session session = this.sessionFactory.getCurrentSession();
        Query<LibraryDTO> query = session.createQuery(" select new com.CourseWorkRusut.DTO.UserDTO() From User user where type(user) in :types", LibraryDTO.class);
        query.setFirstResult(Integer.valueOf(offset));
        query.setMaxResults(Integer.valueOf(offset)+25);

        return query.list();
    }
}
