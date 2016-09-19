package com.example.mrrodriguez.trianadvisor.greendao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.example.mrrodriguez.trianadvisor.greendao.Usuario;
import com.example.mrrodriguez.trianadvisor.greendao.Sitio;
import com.example.mrrodriguez.trianadvisor.greendao.Valoracion;
import com.example.mrrodriguez.trianadvisor.greendao.Comentario;

import com.example.mrrodriguez.trianadvisor.greendao.UsuarioDao;
import com.example.mrrodriguez.trianadvisor.greendao.SitioDao;
import com.example.mrrodriguez.trianadvisor.greendao.ValoracionDao;
import com.example.mrrodriguez.trianadvisor.greendao.ComentarioDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig usuarioDaoConfig;
    private final DaoConfig sitioDaoConfig;
    private final DaoConfig valoracionDaoConfig;
    private final DaoConfig comentarioDaoConfig;

    private final UsuarioDao usuarioDao;
    private final SitioDao sitioDao;
    private final ValoracionDao valoracionDao;
    private final ComentarioDao comentarioDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        usuarioDaoConfig = daoConfigMap.get(UsuarioDao.class).clone();
        usuarioDaoConfig.initIdentityScope(type);

        sitioDaoConfig = daoConfigMap.get(SitioDao.class).clone();
        sitioDaoConfig.initIdentityScope(type);

        valoracionDaoConfig = daoConfigMap.get(ValoracionDao.class).clone();
        valoracionDaoConfig.initIdentityScope(type);

        comentarioDaoConfig = daoConfigMap.get(ComentarioDao.class).clone();
        comentarioDaoConfig.initIdentityScope(type);

        usuarioDao = new UsuarioDao(usuarioDaoConfig, this);
        sitioDao = new SitioDao(sitioDaoConfig, this);
        valoracionDao = new ValoracionDao(valoracionDaoConfig, this);
        comentarioDao = new ComentarioDao(comentarioDaoConfig, this);

        registerDao(Usuario.class, usuarioDao);
        registerDao(Sitio.class, sitioDao);
        registerDao(Valoracion.class, valoracionDao);
        registerDao(Comentario.class, comentarioDao);
    }
    
    public void clear() {
        usuarioDaoConfig.getIdentityScope().clear();
        sitioDaoConfig.getIdentityScope().clear();
        valoracionDaoConfig.getIdentityScope().clear();
        comentarioDaoConfig.getIdentityScope().clear();
    }

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public SitioDao getSitioDao() {
        return sitioDao;
    }

    public ValoracionDao getValoracionDao() {
        return valoracionDao;
    }

    public ComentarioDao getComentarioDao() {
        return comentarioDao;
    }

}
