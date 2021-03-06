/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.coursemaker.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "trainee")
@NamedQueries({
    @NamedQuery(name = "Trainee.findByClass", query = "select a from Trainee a "
                + "where a.trainingClass.trainingClassID = :id "
                + " and a.activeFlag is null"),
    @NamedQuery(name = "Trainee.findByCompany", query = "select a from Trainee a where a.company.companyID = :id "
                + " and a.activeFlag is null"),
    @NamedQuery(name = "Trainee.findByInstructor", 
        query = "select a from Trainee a, InstructorClass b "
                + " where a.trainingClass = b.trainingClass "
                + " and b.instructor.instructorID = :id "
                + " order by a.lastName, a.firstName"),
@NamedQuery(name = "Trainee.login", 
        query = "select a from Trainee a "
                    + "where a.email = :email and a.password = :pswd")})
public class Trainee implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainee", fetch = FetchType.EAGER)
    private List<TeamMember> teamMemberList;
    @OneToMany(mappedBy = "trainee", fetch = FetchType.EAGER)
    private List<DemoAnnouncement> demoAnnouncementList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainee", fetch = FetchType.EAGER)
    private List<TraineeShout> traineeShoutList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainee")
    private List<GuardianTrainee> guardianTraineeList;
    @OneToMany(mappedBy = "trainee")
    private List<GcmDevice> gcmDeviceList;
    @Size(max = 100)
    @Column(name = "GCMRegistrationID")
    private String gCMRegistrationID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainee")
    private List<Attendance> attendanceList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "traineeID")
    private Integer traineeID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "firstName")
    private String firstName;
    @Size(max = 45)
    @Column(name = "middleName")
    private String middleName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "lastName")
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cellphone")
    private String cellphone;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateRegistered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;
    @Column(name = "dateUpdated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;
    @Column(name = "gender")
    private Integer gender;
    @Size(max = 45)
    @Column(name = "IDNumber")
    private String iDNumber;
    @Size(max = 255)
    @Column(name = "address")
    private String address;
    
    @Size(min = 1, max = 45)
    @Column(name = "password")
    private String password;
    @Column(name = "activeFlag")
    private Integer activeFlag;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainee")
    private List<TraineeRating> traineeRatingList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainee")
    private List<TraineeStatus> traineeStatusList;
    @JoinColumn(name = "administratorID", referencedColumnName = "administratorID")
    @ManyToOne
    private Administrator administrator;
    @JoinColumn(name = "trainingClassID", referencedColumnName = "trainingClassID")
    @ManyToOne
    private TrainingClass trainingClass;
    @JoinColumn(name = "cityID", referencedColumnName = "cityID")
    @ManyToOne(optional = false)
    private City city;
    @JoinColumn(name = "companyID", referencedColumnName = "companyID")
    @ManyToOne
    private Company company;
    @JoinColumn(name = "institutionID", referencedColumnName = "institutionID")
    @ManyToOne
    private Institution institution;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainee")
    private List<CourseTrainee> courseTraineeList;
    @OneToMany(mappedBy = "trainee")
    private List<LessonResource> lessonResourceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainee")
    private List<TraineeEquipment> traineeEquipmentList;

    public Trainee() {
    }

    public Trainee(Integer traineeID) {
        this.traineeID = traineeID;
    }

    public Trainee(Integer traineeID, String firstName, String lastName, String email, String cellphone, Date dateRegistered, String password) {
        this.traineeID = traineeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cellphone = cellphone;
        this.dateRegistered = dateRegistered;
        this.password = password;
    }

    public Integer getTraineeID() {
        return traineeID;
    }

    public void setTraineeID(Integer traineeID) {
        this.traineeID = traineeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getgCMRegistrationID() {
        return gCMRegistrationID;
    }

    public void setgCMRegistrationID(String gCMRegistrationID) {
        this.gCMRegistrationID = gCMRegistrationID;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getIDNumber() {
        return iDNumber;
    }

    public void setIDNumber(String iDNumber) {
        this.iDNumber = iDNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Integer activeFlag) {
        this.activeFlag = activeFlag;
    }

    public List<TraineeRating> getTraineeRatingList() {
        return traineeRatingList;
    }

    public void setTraineeRatingList(List<TraineeRating> traineeRatingList) {
        this.traineeRatingList = traineeRatingList;
    }

    public List<TraineeStatus> getTraineeStatusList() {
        return traineeStatusList;
    }

    public void setTraineeStatusList(List<TraineeStatus> traineeStatusList) {
        this.traineeStatusList = traineeStatusList;
    }

   

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public TrainingClass getTrainingClass() {
        return trainingClass;
    }

    public void setTrainingClass(TrainingClass trainingClass) {
        this.trainingClass = trainingClass;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

  
    public List<CourseTrainee> getCourseTraineeList() {
        return courseTraineeList;
    }

    public void setCourseTraineeList(List<CourseTrainee> courseTraineeList) {
        this.courseTraineeList = courseTraineeList;
    }

    public List<LessonResource> getLessonResourceList() {
        return lessonResourceList;
    }

    public void setLessonResourceList(List<LessonResource> lessonResourceList) {
        this.lessonResourceList = lessonResourceList;
    }

    public List<TraineeEquipment> getTraineeEquipmentList() {
        return traineeEquipmentList;
    }

    public void setTraineeEquipmentList(List<TraineeEquipment> traineeEquipmentList) {
        this.traineeEquipmentList = traineeEquipmentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (traineeID != null ? traineeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trainee)) {
            return false;
        }
        Trainee other = (Trainee) object;
        if ((this.traineeID == null && other.traineeID != null) || (this.traineeID != null && !this.traineeID.equals(other.traineeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.coursemaker.data.Trainee[ traineeID=" + traineeID + " ]";
    }

    public String getGCMRegistrationID() {
        return gCMRegistrationID;
    }

    public void setGCMRegistrationID(String gCMRegistrationID) {
        this.gCMRegistrationID = gCMRegistrationID;
    }

    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    public List<GcmDevice> getGcmDeviceList() {
        return gcmDeviceList;
    }

    public void setGcmDeviceList(List<GcmDevice> gcmDeviceList) {
        this.gcmDeviceList = gcmDeviceList;
    }

    public List<GuardianTrainee> getGuardianTraineeList() {
        return guardianTraineeList;
    }

    public void setGuardianTraineeList(List<GuardianTrainee> guardianTraineeList) {
        this.guardianTraineeList = guardianTraineeList;
    }

    public List<TraineeShout> getTraineeShoutList() {
        return traineeShoutList;
    }

    public void setTraineeShoutList(List<TraineeShout> traineeShoutList) {
        this.traineeShoutList = traineeShoutList;
    }

    public List<TeamMember> getTeamMemberList() {
        return teamMemberList;
    }

    public void setTeamMemberList(List<TeamMember> teamMemberList) {
        this.teamMemberList = teamMemberList;
    }

    public List<DemoAnnouncement> getDemoAnnouncementList() {
        return demoAnnouncementList;
    }

    public void setDemoAnnouncementList(List<DemoAnnouncement> demoAnnouncementList) {
        this.demoAnnouncementList = demoAnnouncementList;
    }
    
}
