package controllers.admincontrollers;

/**
 * Created by derdus on 6/17/16.
 */
import models.web.CourseField;
import play.data.Form;
import static play.data.Form.form;
import views.html.admin.courseFieldFormView;
import play.mvc.*;
import play.Logger;




public class CourseActions extends Controller{
    static Form<CourseField> courseFieldForm = form(CourseField.class);

    public static Result newCourseField(){
        //return ok(courseFieldFormView.render(courseFieldForm));
        return ok(courseFieldFormView.render(courseFieldForm));
    }

    public static Result saveCourseField(){
        Form<CourseField> courseFieldBoundForm = courseFieldForm.bindFromRequest();
        if(courseFieldBoundForm.hasErrors()){
            flash("coursefieldformerrors","Correct the errors and submit again");
            return badRequest(courseFieldFormView.render(courseFieldBoundForm));
        }
        CourseField courseField = courseFieldBoundForm.get();
        Logger.info("Id is " + courseField.course_field_id);
        courseField.saveCourseField();
        return redirect(routes.CourseActions.newCourseField());
    }

    public static Result editCourseField(Long id){return TODO;}

    public static Result newCourseLevel(){
        return TODO;
    }
}
