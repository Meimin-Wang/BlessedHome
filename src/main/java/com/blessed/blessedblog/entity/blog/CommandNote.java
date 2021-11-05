package com.blessed.blessedblog.entity.blog;

import com.blessed.blessedblog.entity.User;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @ClassName CommondNote
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/18 : 4:09 下午
 * @Email blessedwmm@gmail.com
 */
@Entity(name = "tbl_commond_note")
@Table(name = "tbl_commond_note")
@Data
@EntityListeners(AuditingEntityListener.class)
public class CommandNote implements Serializable {

    private static final long serialVersionUID = -1644711997859859725L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "command_id")
    private Long commandId;

    @Column(name = "type", length = 30, nullable = false)
    private String commandType;

    @Column(name = "command_name", length = 100, nullable = false)
    private String commandName;

    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateTime;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Parameter> parameters;

    @Column(name = "summary")
    private String summary;

    @Column(name = "details")
    @Lob
    private String details;

    @ManyToOne
    private User author;
}
