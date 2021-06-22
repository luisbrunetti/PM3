package com.example.pm3.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.pm3.models.Competencia;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CompetenciaDao_Impl implements CompetenciaDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Competencia> __insertionAdapterOfCompetencia;

  private final EntityDeletionOrUpdateAdapter<Competencia> __deletionAdapterOfCompetencia;

  public CompetenciaDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCompetencia = new EntityInsertionAdapter<Competencia>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `competencias` (`id`,`name`,`cantidad`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Competencia value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getCantidad() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCantidad());
        }
      }
    };
    this.__deletionAdapterOfCompetencia = new EntityDeletionOrUpdateAdapter<Competencia>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `competencias` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Competencia value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
      }
    };
  }

  @Override
  public void insertCompetencia(final Competencia... comp) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCompetencia.insert(comp);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteCompetencia(final Competencia comp) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfCompetencia.handle(comp);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Competencia> getAllCompetencias() {
    final String _sql = "SELECT * FROM competencias";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfCantidad = CursorUtil.getColumnIndexOrThrow(_cursor, "cantidad");
      final List<Competencia> _result = new ArrayList<Competencia>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Competencia _item;
        final String _tmpId;
        _tmpId = _cursor.getString(_cursorIndexOfId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpCantidad;
        _tmpCantidad = _cursor.getString(_cursorIndexOfCantidad);
        _item = new Competencia(_tmpId,_tmpName,_tmpCantidad);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
