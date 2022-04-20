package com.travel.admin.service.impl;

import com.travel.admin.mapper.CustormMapper;
import com.travel.admin.mapper.NotesMapper;
import com.travel.admin.service.INotesService;
import com.travel.common.domain.Custorm;
import com.travel.common.domain.Notes;
import com.travel.common.query.NotesQuery;
import com.travel.common.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @description: TODO
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class NotesServiceImpl implements INotesService {

    @Autowired
    private NotesMapper notesMapper;

    @Autowired
    private CustormMapper custormMapper;


    @Override
    @Transactional
    public void saveNotes(Notes notes) {
        notes.setCreatetime(LocalDateTime.now());
        notesMapper.saveNotes(notes);
    }

    @Override
    public PageList getNotesPageList(NotesQuery notesQuery) {
        //查询游记的总数
        Long total  =  notesMapper.queryTotal(notesQuery);
        //查询游记的每页显示的数据
        List<Notes> notesList = notesMapper.queryData(notesQuery);
        notesList.forEach(note->{
            Custorm custorm  = custormMapper.queryCustormById(note.getCreatorid());
            note.setCustorm(custorm);
        });
        PageList pageList = new PageList(notesQuery.getCurrentPage(),total,notesList);
        return pageList;
    }

    @Override
    public Notes getNotesById(NotesQuery notesQuery) {
        Notes notes = notesMapper.getNotesById(notesQuery);
        Custorm custorm = custormMapper.queryCustormById(notes.getCreatorid());
        notes.setCustorm(custorm);
        return notes;
    }

    @Override
    public void updateNotesViewNum(Long id) {
        notesMapper.updateNotesViewNum(id);
    }


}
