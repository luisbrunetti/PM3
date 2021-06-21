package com.example.pm3.dao;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H\'J!\u0010\b\u001a\u00020\u00032\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\t\"\u00020\u0005H\'\u00a2\u0006\u0002\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lcom/example/pm3/dao/CompetenciaDao;", "", "deleteCompetencia", "", "comp", "Lcom/example/pm3/models/Competencia;", "getAllCompetencias", "", "insertCompetencia", "", "([Lcom/example/pm3/models/Competencia;)V", "app_debug"})
public abstract interface CompetenciaDao {
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM competencias")
    public abstract java.util.List<com.example.pm3.models.Competencia> getAllCompetencias();
    
    @androidx.room.Insert()
    public abstract void insertCompetencia(@org.jetbrains.annotations.NotNull()
    com.example.pm3.models.Competencia... comp);
    
    @androidx.room.Delete()
    public abstract void deleteCompetencia(@org.jetbrains.annotations.NotNull()
    com.example.pm3.models.Competencia comp);
}