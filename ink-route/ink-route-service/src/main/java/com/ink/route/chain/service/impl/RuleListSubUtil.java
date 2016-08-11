package com.ink.route.chain.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.ink.route.api.model.po.AsileInfo;

/**
 * Created by huohb on 2016/6/1.
 */
public class RuleListSubUtil {

    /**
     * 从sourceList中移除掉所有asileCode在subSet中的元素
     * @param sourceList
     * @param subSet
     */
    public static void subList(List<AsileInfo> sourceList, Set<String> subSet){

        Iterator<AsileInfo> iterator = sourceList.iterator();
        while (iterator.hasNext()){
            if(subSet.contains(iterator.next().getAsileCode())){
                iterator.remove();
            }
        }

    }
    /**
     * 从sourceList中移除掉所有asileCode不在remainsSet中的元素
     * @param sourceList
     * @param remainsSet
     */
    public static void remainsList(List<AsileInfo> sourceList, Set<String> remainsSet){

        Iterator<AsileInfo> iterator = sourceList.iterator();
        while (iterator.hasNext()){
            if(!remainsSet.contains(iterator.next().getAsileCode())){
                iterator.remove();
            }
        }

    }

}
