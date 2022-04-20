package com.travel.admin.service;



import com.travel.common.domain.Level;

import java.util.List;

/**
 * @description: ILevelService
 * @date: created by 2020/10/25 21:17
 */
public interface ILevelService {
    List<Level> findAllLevel();
}
