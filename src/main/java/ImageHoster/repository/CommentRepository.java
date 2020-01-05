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

    public List<Comment> getCommentByImageId(Integer imageId){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Comment> commentTypedQuery = em.createQuery("SELECT c FROM Comment c WHERE c.image =:imageId", Comment.class).setParameter("imageId", imageId);
        List<Comment> resultList = commentTypedQuery.getResultList();
        return resultList;
    }

}
