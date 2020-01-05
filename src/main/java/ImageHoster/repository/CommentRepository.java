package ImageHoster.repository;

import ImageHoster.model.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class CommentRepository {

    //Get an instance of EntityManagerFactory from persistence unit with name as 'imageHoster'
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    //This method accepts the comment object and persist the same in comments table in the imageHoster DB
    //Create instance of an Entity Manager
    //Create a transaction
    //Begin the transaction
    //Persist the comment object
    //Commit the transaction if there are no exceptions
    //Otherwise rollback the transaction.
    public Comment addComment(Comment comment){
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction =  em.getTransaction();

        try{
            transaction.begin();
            em.persist(comment);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }

        return comment;
    }

}
