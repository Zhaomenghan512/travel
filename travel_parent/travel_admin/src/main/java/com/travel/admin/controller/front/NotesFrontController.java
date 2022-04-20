package com.travel.admin.controller.front;


import com.travel.admin.service.INotesService;
import com.travel.common.domain.Notes;
import com.travel.common.query.NotesQuery;
import com.travel.common.util.PageList;
import com.travel.common.util.ResResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:NotesFrontController
 * @date: created by 2020/10/26 8:15
 */
@Controller
public class NotesFrontController {


    @Autowired
    private INotesService notesService;


    @RequestMapping("/front/notes/saveNotes")
    @ResponseBody
    public ResResult saveNotes(@RequestBody Notes notes){
        try {
            notesService.saveNotes(notes);
            return new ResResult(20000,"操作成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResResult(50000,"操作失败");
    }

    @RequestMapping("/front/notes/page")
    @ResponseBody
    public ResResult getNotesPageList(@RequestBody NotesQuery notesQuery){
        try {
            PageList notesPageList = notesService.getNotesPageList(notesQuery);
            return new ResResult(20000,"操作成功",notesPageList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResResult(50000,"操作失败");
    }

    @RequestMapping("/front/notes/getNotesById")
    @ResponseBody
    public ResResult getNotesById(@RequestBody NotesQuery notesQuery){
        try {
            //更新浏览次数
            notesService.updateNotesViewNum(notesQuery.getId());
            Notes notes = notesService.getNotesById(notesQuery);
            return new ResResult(20000,"操作成功",notes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResResult(50000,"操作失败");
    }




}
