# 文件上传
## 项目打包
- 预发环境  
  `mvn clean package -Dmaven.test.skip=true  -Pstage`
- 生产环境  
  `mvn clean package -Dmaven.test.skip=true  -Pproduct`

## 上传图片
### 接口地址
`POST /api/img`

### 请求参数
|字段		|必传		|说明		|
|--------|--------|--------|
|file    |是       |上传文件 |
|type    |是       |图片分类类型 |
|path    |是       |保存路径，/开头，路径支持数字、大小写字母、_、- |
|fileName|否	   |文件名，有值则返回地址中保留该值  |

### 图片分类类型
- COMMON：图片通用类型，原样上传
- IMG3：图片上传后会生成大中小三张图,大图 <code>big</code> 800\*800，中图 <code>middle</code> 300\*300，小图 <code>small</code> 100\*100，返回中图图片地址
- USER_HEAD：用户头像

### 图片类型
`BMP、GIF、JPG、JPEG、PNG`

## 上传媒体文件
### 接口地址
`POST /api/media`

### 请求参数
|字段		|必传		|说明		|
|--------|--------|--------|
|file    |是       |上传文件 |
|type    |是       |媒体文件分类类型 |
|path    |是       |保存路径，/开头，路径支持数字、大小写字母、_、- |
|fileName|否	   |文件名，有值则返回地址中保留该值  |

### 媒体文件分类类型
- AUDIO(音频文件)

### 媒体文件类型
```
// 媒体文件类型
SWF, FLV, MP3, WAV, WMA, WMV, MPG, ASF, RM, AMR, MID, APE,
// 视频格式
MP4, AVI, RMVB
```
