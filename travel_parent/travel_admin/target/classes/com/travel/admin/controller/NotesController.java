package com.travel.admin.controller;


import com.travel.admin.service.INotesService;
import com.travel.common.query.NotesQuery;
import com.travel.common.util.PageList;
import com.travel.common.util.ResResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Notes游记
 */
@RestController
@RequestMapping("/notes")
public class NotesController {


    @Autowired
    private INotesService notesService;


    //查询所有游记
    @PostMapping("/page")
    public ResResult getNotesList(NotesQuery notesQuery){
        try {
            PageList notesPageList = notesService.getNotesPageList(notesQuery);
            return new ResResult(ResResult.CodeStatus.OK,"操作成功",notesPageList);
        }catch (Exception e){
            return new ResResult(ResResult.CodeStatus.ILLEGAL_REQUEST,"操作失败");
        }
    }









}
