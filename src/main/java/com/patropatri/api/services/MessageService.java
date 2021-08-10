package com.patropatri.api.services;

import com.patropatri.api.models.Notification;
import org.springframework.data.domain.Page;

public interface MessageService {

    Page<Notification> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
