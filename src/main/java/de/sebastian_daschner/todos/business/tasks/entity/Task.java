/*
 * Copyright (C) 2015 Sebastian Daschner, sebastian-daschner.de
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.sebastian_daschner.todos.business.tasks.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@NamedQuery(name = "Task.findAll", query = "SELECT t from tasks t ORDER BY t.finished ASC, t.priority DESC, t.updated DESC, t.name ASC")
@Entity(name = "tasks")
public class Task {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Size(min = 1, max = 255)
    @Basic(optional = false)
    private String name;

    @Contexts
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> contexts;

    private Integer priority;

    @Basic(optional = false)
    private boolean finished;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @PrePersist
    @PreUpdate
    public void updateDate() {
        this.updated = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getContexts() {
        return contexts;
    }

    public void setContexts(Set<String> contexts) {
        this.contexts = contexts;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contexts=" + contexts +
                ", priority=" + priority +
                ", finished=" + finished +
                ", updated=" + updated +
                ", dueDate=" + dueDate +
                '}';
    }

}
