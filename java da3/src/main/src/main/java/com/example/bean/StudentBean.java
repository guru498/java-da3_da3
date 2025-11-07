package com.example.bean;

import com.example.dao.StudentDAO;
import com.example.model.Student;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "studentBean")
@SessionScoped
public class StudentBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Student student = new Student();
    private StudentDAO dao = new StudentDAO();
    private List<Student> students;

    // Action result navigation strings
    public String toRegistration() {
        student = new Student();
        return "registration.xhtml?faces-redirect=true";
    }

    public String submitRegistration() {
        dao.create(student);
        return "confirmation.xhtml?faces-redirect=true";
    }

    public String toDashboard() {
        students = dao.findAll();
        return "dashboard.xhtml?faces-redirect=true";
    }

    public String edit() {
        String idStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        if (idStr == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Missing student id.", null));
            return "dashboard.xhtml?faces-redirect=true";
        }
        try {
            Long id = Long.parseLong(idStr);
            Student s = dao.findById(id);
            if (s == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Student not found.", null));
                return "dashboard.xhtml?faces-redirect=true";
            }
            this.student = s;
            return "registration.xhtml?faces-redirect=true";
        } catch (NumberFormatException nfe) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid student id.", null));
            return "dashboard.xhtml?faces-redirect=true";
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error loading student: " + ex.getMessage(), null));
            return "dashboard.xhtml?faces-redirect=true";
        }
    }

    public String update() {
        dao.update(student);
        return toDashboard();
    }

    public String delete() {
        String idStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        if (idStr == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Missing student id.", null));
            return "dashboard.xhtml?faces-redirect=true";
        }
        try {
            Long id = Long.parseLong(idStr);
            dao.delete(id);
            // refresh list
            students = dao.findAll();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Student deleted.", null));
        } catch (NumberFormatException nfe) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid student id.", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete failed: " + ex.getMessage(), null));
        }
        return "dashboard.xhtml?faces-redirect=true";
    }

    // getters/setters
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Student> getStudents() {
        if (students == null) {
            try {
                students = dao.findAll();
            } catch (Exception ex) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error loading students: " + ex.getMessage(),
                                null));
            }
        }
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
