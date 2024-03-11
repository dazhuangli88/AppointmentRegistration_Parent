import request from '@/utils/request'
import axios from 'axios';
export default{
    //数据字典列表
    dictList(Id){
        return request({
            url: `/admin/cmn/dict/findChildData/${Id}`,
            method: 'GET'
          
          })
    },
 
}

