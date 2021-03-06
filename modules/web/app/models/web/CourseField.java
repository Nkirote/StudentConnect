package models.web;

import play.Logger;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.*;
import play.data.validation.Constraints;

/**
 * Created by derdus on 6/17/16.
 */
/*
    values such as
    Engineering
    Medical
    Information Technology
    Business
    Social sciences
    Education
    ---Could be called course category
 */
    @Entity
public class CourseField extends Model {
    @Id
    public Long course_field_id;
    @Constraints.Required(message = "Please enter field name e.g. Business, medical etc.")
    public String course_field_name;
    @Constraints.Required(message = "Please enter field name e.g. Business, medical etc.")
    @Column(columnDefinition = "TEXT")
    public String course_field_description;


    //Entity Relations
    @OneToMany(mappedBy = "courseField")
    public List<Course> courseList;


    //methods
    public static Finder<Long, CourseField> find(){
        return new Finder<Long, CourseField>(Long.class,CourseField.class);
    }

    /**
     *
     * @return
     */
    public Long saveCourseField(){
        if(this.course_field_id == null){
            save();
            return course_field_id;
        }
        update();
        return course_field_id;
    }

    public CourseField getCourseById(Long id){
        return CourseField.find().byId(id);
    }

    public List<CourseField> fetchAllCourseFields(){
        return find().all();
    }

    public boolean deleteCourseField(Long id){
        if(getCourseById(id) != null){
            try {
                getCourseById(id).delete();
                return true;
            }catch (PersistenceException pe){
                Logger.error("Error:" + pe.getMessage().toString());
                return false;
            }catch (Exception ex){
                Logger.error("Error:" + ex.getMessage().toString());
                return false;
            }

        }
        return false;
    }
    public Map<Map<Long,String>,Boolean> fetchCourseFieldMap(){
        List<CourseField> courseFieldList = find().orderBy("course_field_name").findList();
        Map<Map<Long,String>,Boolean> courseFieldMap = new LinkedHashMap<Map<Long,String>,Boolean>();
        for(int i = 0; i < courseFieldList.size(); i++){
            Map<Long,String> innerCourseFieldMap  = new HashMap<Long,String>();
            innerCourseFieldMap.put(courseFieldList.get(i).course_field_id,courseFieldList.get(i).course_field_name);
            courseFieldMap.put(innerCourseFieldMap,false);
        }
        return courseFieldMap;
    }

    public Map<Map<Long,String>,Boolean> fetchCourseFieldMap(Long id){
        List<CourseField> courseFieldList = find().orderBy("course_field_name").findList();
        Map<Map<Long,String>,Boolean> courseFieldMap = new LinkedHashMap<Map<Long,String>,Boolean>();
        for(int i = 0; i < courseFieldList.size(); i++){
            Map<Long,String> innerCourseFieldMap  = new HashMap<Long,String>();
            innerCourseFieldMap.put(courseFieldList.get(i).course_field_id,courseFieldList.get(i).course_field_name);
            if(courseFieldList.get(i).course_field_id == id){
                courseFieldMap.put(innerCourseFieldMap,true);
            }else {
                courseFieldMap.put(innerCourseFieldMap,false);
            }
        }
        return courseFieldMap;
    }

}
