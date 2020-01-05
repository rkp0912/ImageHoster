package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

public class CommentController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String addComment(@PathVariable(name = "imageId") Integer imageId, @RequestParam(name="comment") String comment, Model model){
        Comment newComment = new Comment();
        Image image = imageService.getImage(imageId);
        newComment.setText(comment);
        newComment.setImage(image);
        newComment.setCreatedDate(new Date());
        newComment.setUser(image.getUser());
        commentService.addComment(newComment);
        return "redirect:/images/" +image.getId()+"/"+ image.getTitle();
    }
}
