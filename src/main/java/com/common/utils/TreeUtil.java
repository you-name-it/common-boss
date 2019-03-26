package com.common.utils;

import com.common.base.common.model.JSTreeEntity;
import com.common.base.common.model.Select2Entity;
import com.common.base.resource.entity.TbResource;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TreeUtil {

    /*
	 * select2下拉组件数据对象
	 */
    private List<Select2Entity> selectTree = new ArrayList<Select2Entity>();
    /*
     * 生成select2下拉组件数据时遍历的次数
     */
    private int selectCnt = 0;

    public List<JSTreeEntity> generateJSTree(List<TbResource> resourceList)
    {
        List<JSTreeEntity> jstreeList = new ArrayList<JSTreeEntity>();

        for (TbResource resource : resourceList) {
            JSTreeEntity jstree = new JSTreeEntity();
            jstree.setId(resource.getId().toString());
            jstree.setParent(resource.getParentId()==null ? "#" : resource.getParentId().toString());
            jstree.setText(resource.getName());
            jstree.setIcon(StringUtils.isBlank(resource.getIcon()) ? "am-icon-cog" : resource.getIcon());
            JSTreeEntity.State state = new JSTreeEntity().new State();
            state.setDisabled(false);
            state.setSelected(resource.isSelected());
            state.setOpened(true);
            jstree.setState(state);
            jstreeList.add(jstree);
        }
        return jstreeList;
    }

    /**
     * 根据父节点的ID获取所有子节点
     * @param list	具有树形结构特点的集合
     * @param parentId	父节点ID
     * @return	树形结构集合
     */
    public List<Select2Entity> getSelectTree(List<TbResource> list,Integer parentId) {
        List<TbResource> treeMenuList = treeMenuList(list, parentId);
        recursionForSelect(treeMenuList);
        return selectTree;
    }

    /**
     * 递归列表
     * @param list
     */
    private void recursionForSelect(List<TbResource> list) {
        String str = "";
        for(int i=0; i< selectCnt; i++)
        {
            str += "├┈┈┈";
        }
        for (TbResource re : list) {
            if(null == re.getParentId())
            {
                str = "";
                selectCnt = 0;
            }
            Select2Entity se = new Select2Entity();
            se.setId(re.getId().toString());
            se.setText(str + re.getName());
            se.setName(re.getName());
            selectTree.add(se);
            if(re.getChildren().size() > 0)
            {
                selectCnt ++;
                recursionForSelect(re.getChildren());
            }
        }
    }

    /**
     * 菜单树递归
     * @param menuList
     * @param parentId
     * @return
     */
    public List<TbResource> treeMenuList(List<TbResource> menuList, Integer parentId) {
        List<TbResource> childMenu = new ArrayList<TbResource>();
        for (TbResource menu : menuList) {
            Integer menuId = menu.getId();
            Integer pid = menu.getParentId();
            if (parentId == pid) {
                List<TbResource> c_node = treeMenuList(menuList, menuId);
                menu.setChildren(c_node);
                childMenu.add(menu);
            }
        }
        return childMenu;
    }
}
