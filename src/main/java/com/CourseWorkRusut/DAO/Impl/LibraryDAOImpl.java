package com.CourseWorkRusut.DAO.Impl;

import com.CourseWorkRusut.DAO.LibraryDAO;
import com.CourseWorkRusut.DTO.LibraryDTO;
import com.CourseWorkRusut.model.Author;
import com.CourseWorkRusut.model.Library;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
    @Deprecated
    public List<LibraryDTO> getAllLibrary(String offset) {                                  //пощупать select, в палне колекций,  select library.name, library.authors, авторы не работают, потому что коллекция
        Session session = this.sessionFactory.getCurrentSession();                          //попробоавать определить явно, как в тырнете советуют with element property reference, illegal attempt to dereference collection ключевые слова ошибки
        Query query = session.createQuery(                                                  //делает копии, похоже нужен все таки set
                "select library From Library library join fetch library.authors aut")    //дуплитакаты это проблема manyToMany? эх, проверить бы на остальных
        .unwrap(Query.class)                                                                //попробовать с переопределнной set и иквл в модели проверить ЗАНОГО
        .setResultTransformer(new ResultTransformer() {

            Set<LibraryDTO> set = new LinkedHashSet<>();

            @Override
            public Object transformTuple(Object[] objects, String[] strings) {
                Long id = ((Library)objects[0]).getLibraryId();
                String name = ((Library)objects[0]).getName();
                LocalDate localDate = ((Library)objects[0]).getData();

                List<String> list = new ArrayList<>();
                for(Author author: ((Library)objects[0]).getAuthors()) {

                    if (author.getMiddlename() == null)
                        author.setMiddlename("");

                    list.add(author.getSurname() +" " + author.getName() + " " + author.getMiddlename());
                }

                LibraryDTO libraryDTO = new LibraryDTO(id,name,localDate,list);

                set.add(libraryDTO);

                return libraryDTO;
            }

            @Override
            public List transformList(List list) {
                return new ArrayList<>(set);
            }
        });

        query.setFirstResult(Integer.valueOf(offset));
        query.setMaxResults(Integer.valueOf(offset)+25);

        return query.getResultList();
    }
}

























