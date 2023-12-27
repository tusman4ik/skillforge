package com.skillforge.entities;

import com.skillforge.enums.TaskType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@ToString
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "taskType", length = 225)
    private TaskType taskType;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "solve", length = 10000)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String solve;

    @Column(name = "answer")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String answer;

    @Column(name = "analytics_list")
    private String analyticsList;

}