package com.deltacode.kcb.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(	name = "complains_tbl")
@SQLDelete(sql = "UPDATE complains_tbl SET is_deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Complain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private  UserAccType userAccType;

    @ManyToOne
    private  ComplaintType complaintType;
    @Column(name="account_no")
    private String accountNo;

    @Column(name="subject")
    private String subject;

    @Column(name="message")
    private String message;

    @Column(name="created_by")
    private Long createdBy;

    @Column(name="created_on")
    private Date createdOn;

    @Column(name="updated_on")
    private Date updatedOn;

    @Column(name="updated_by")
    private Long updatedBy;

    @Column(name="complain_attachment_path")
    private String complainAttachmentPath;
}
