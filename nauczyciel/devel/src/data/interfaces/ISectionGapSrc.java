package data.interfaces;

import data.bean.SectionGapSrcBean;
import java.util.List;

/**
 *
 * @author michal
 */
public interface ISectionGapSrc
{
    void save (SectionGapSrcBean bean)
            throws Exception;

    void delete (int taskId)
            throws Exception;

    void update (SectionGapSrcBean bean)
            throws Exception;

    List<SectionGapSrcBean> get (int taskId)
            throws Exception;
}
