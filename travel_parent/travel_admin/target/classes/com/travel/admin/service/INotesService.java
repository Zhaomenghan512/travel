package com.travel.admin.service;


import com.travel.common.domain.Notes;
import com.travel.common.query.NotesQuery;
import com.travel.common.util.PageList;

/**
 * @description: TODO
 */
public interface INotesService {

    void saveNotes(Notes notes);


    PageList getNotesPageList(NotesQuery notesQuery);

    Notes getNotesById(NotesQuery notesQuery);


    void updateNotesViewNum(Long id);
}