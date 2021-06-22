package com.example.pm3.dbs;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.example.pm3.dao.CompetenciaDao;
import com.example.pm3.dao.CompetenciaDao_Impl;
import com.example.pm3.dao.EquipoDao;
import com.example.pm3.dao.EquipoDao_Impl;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CompetenciaDb_Impl extends CompetenciaDb {
  private volatile CompetenciaDao _competenciaDao;

  private volatile EquipoDao _equipoDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `competencias` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `cantidad` TEXT NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Equipo` (`id` TEXT NOT NULL, `numEquipo` TEXT NOT NULL, `name` TEXT NOT NULL, `url` TEXT NOT NULL, `anio` TEXT NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'b6318a824ca21a829352852aaf8ca9ee')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `competencias`");
        _db.execSQL("DROP TABLE IF EXISTS `Equipo`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsCompetencias = new HashMap<String, TableInfo.Column>(3);
        _columnsCompetencias.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompetencias.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompetencias.put("cantidad", new TableInfo.Column("cantidad", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCompetencias = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCompetencias = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCompetencias = new TableInfo("competencias", _columnsCompetencias, _foreignKeysCompetencias, _indicesCompetencias);
        final TableInfo _existingCompetencias = TableInfo.read(_db, "competencias");
        if (! _infoCompetencias.equals(_existingCompetencias)) {
          return new RoomOpenHelper.ValidationResult(false, "competencias(com.example.pm3.models.Competencia).\n"
                  + " Expected:\n" + _infoCompetencias + "\n"
                  + " Found:\n" + _existingCompetencias);
        }
        final HashMap<String, TableInfo.Column> _columnsEquipo = new HashMap<String, TableInfo.Column>(5);
        _columnsEquipo.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEquipo.put("numEquipo", new TableInfo.Column("numEquipo", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEquipo.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEquipo.put("url", new TableInfo.Column("url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEquipo.put("anio", new TableInfo.Column("anio", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEquipo = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEquipo = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEquipo = new TableInfo("Equipo", _columnsEquipo, _foreignKeysEquipo, _indicesEquipo);
        final TableInfo _existingEquipo = TableInfo.read(_db, "Equipo");
        if (! _infoEquipo.equals(_existingEquipo)) {
          return new RoomOpenHelper.ValidationResult(false, "Equipo(com.example.pm3.models.Equipo).\n"
                  + " Expected:\n" + _infoEquipo + "\n"
                  + " Found:\n" + _existingEquipo);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "b6318a824ca21a829352852aaf8ca9ee", "4c2294cdd9f330b413bf8054570aaeed");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "competencias","Equipo");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `competencias`");
      _db.execSQL("DELETE FROM `Equipo`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public CompetenciaDao competenciaDao() {
    if (_competenciaDao != null) {
      return _competenciaDao;
    } else {
      synchronized(this) {
        if(_competenciaDao == null) {
          _competenciaDao = new CompetenciaDao_Impl(this);
        }
        return _competenciaDao;
      }
    }
  }

  @Override
  public EquipoDao equipoDao() {
    if (_equipoDao != null) {
      return _equipoDao;
    } else {
      synchronized(this) {
        if(_equipoDao == null) {
          _equipoDao = new EquipoDao_Impl(this);
        }
        return _equipoDao;
      }
    }
  }
}
