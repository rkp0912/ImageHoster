package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
@Controller
public class CommentController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    //This method adds the comments to the images
    //Fetches the image based on the image_id and user from the session
    //Creates the comment object, updated the same with image fetched using image_id, logged-in user, comment text
    //Make a call to commentService class to persist the object in the database.
    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String addComment(@PathVariable(name = "imageId") Integer imageId, @RequestParam(name="comment") String comment, Model model, HttpSession session){
        Comment newComment = new Comment();
        Image image = imageService.getImage(imageId);
        newComment.setText(comment);
        newComment.setImage(image);
        newComment.setCreatedDate(new Date());
        User loggedUser = (User)session.getAttribute("loggeduser");
        newComment.setUser(loggedUser);
        commentService.addComment(newComment);
        return "redirect:/images/" +image.getId()+"/"+ image.getTitle();
    }
}
