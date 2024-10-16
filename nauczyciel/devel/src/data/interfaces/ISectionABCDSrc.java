package data.interfaces;

import data.bean.SectionABCDSrcBean;
import java.util.List;

/**
 *
 * @author michal
 */
public interface ISectionABCDSrc
{
    void save (SectionABCDSrcBean bean)
            throws Exception;

    void delete (int id)
            throws Exception;

    void update (SectionABCDSrcBean bean)
            throws Exception;

    List<SectionABCDSrcBean> get (int taskId)
            throws Exception;
}
