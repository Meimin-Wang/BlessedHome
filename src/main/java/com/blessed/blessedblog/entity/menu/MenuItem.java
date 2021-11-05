package com.blessed.blessedblog.entity.menu;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Table("tbl_menu_item")
@Data
@Entity(name = "tbl_menu_item")
@EntityListeners(AuditingEntityListener.class)
public class MenuItem implements Serializable {
    private static final long serialVersionUID = 9121145452619987982L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;
    @Column(name = "menu_name", nullable = false, length = 20)
    private String menuName;
    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;
    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateTime;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_sub_menu_items")
    private Set<MenuItem> subMenuItems;

}
