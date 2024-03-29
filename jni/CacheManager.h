#include <string>
#include <map>
#include <fstream>
#include <jni.h>
#include "debugOut.h"
#include "TimeHelper.h"
#include "FileHelper.h"

#define MB 1024*1024

class CacheManager{
	public:
	    //默认构造函数，设置最大内存值为10MB
	    CacheManager(){
	        maxMemorySize = MB*10;
	    }
		//析构函数，遍历指针集，删除不为空的指针
		~CacheManager();
		//获得内存，指定图片路径
		char* getMemory(std::string);
		//获取图片大小
		int getImageSize(std::string);
		//释放内存，指定图片路径
		void releaseMemory(std::string);
		//设置最大内存值，单位byte
		void setMaxMenory(int);
		//取得当前已分配的内存总数
		int getCurrentMemoryUse();
		//释放内存方案，要求参数为size或id
		//size方案优先释放内存占用大的图片
		//id方案优先释放ID小的图片
		bool autoReleaseMemory(std::string);
		//取得大小最大的图片ID
		std::string getMaxImageSizeID();
		//取得ID最小的图片ID
		std::string getLittleImageID();
	private:
	    int maxMemorySize;
	    //储存内存指针的数据结构，map对象的first存放图片ID，second存放该图片对应的内存块
		std::map<std::string,char*> pointList;
	    std::map<std::string,char*>::iterator MemoryIter;
	    //存放图片大小的数据结构，map对象的first存放图片ID，second存放图片大小，单位byte
	    std::map<std::string,int> ImageSizeList;
	    std::map<std::string,int>::iterator SizeIter;
	    TimeHelper mTime;
	    std::ifstream fp_in;
};
