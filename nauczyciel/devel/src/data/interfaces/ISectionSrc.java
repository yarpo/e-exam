package data.interfaces;

import data.bean.SectionSrcBean;
import java.util.List;

/**
 *
 * @author michal
 */
public interface ISectionSrc
{
    /**
     * zapisuje sekcje do bazy danych
     */
    void save (SectionSrcBean bean)
            throws Exception;

    /**
     * usuwa sekcje z bazy danych
     */
    void delete (int id)
            throws Exception;

    /**
     * nadpisuje istniejaca sekcje
     */
    void update (SectionSrcBean bean)
            throws Exception;

    /**
     * pobiera sekcje dla egzaminu o zadanym id
     */
    List<SectionSrcBean> get (int examId)
            throws Exception;

    /**
     * Zwraca id ostanienie rekordu
     */
    int getLastSectionId ()
            throws Exception;
}
