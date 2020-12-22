# namespace：名称空间，定义编译成的语言，定义编译后Java 的文件的包名 package
namespace java com.chxlay.thrift.generate
# 生成Python 的文件包名的定义
namespace py py.thrift.generate

# typedef： 可以对 Thrift 语言中的 数据类型 与 Java 中的语言类型进行 设置 别名映射关系
// thrift <=======> Java

# i8 即 byte 且不允许使用 byte ,请使用 i8
# typedef byte byte ---->>>> thrift 自由的数据类型 与 映射的类型的名称不能相同，也不需要映射
typedef i16 short
typedef i32 int
typedef i64 long
typedef bool Boolean
typedef bool boolean
typedef string String
# typedef double double  不需要映射相同的类型
typedef set<T> Set
typedef list<T> List
typedef map<K,V> HashMap


// 普通基础类型
const i32 INT32CONSTANT = 9853

// 复杂容器类型
const map<string,string> MAPCONSTANT = {'k1':'v1', 'k2':'v2'};
const set<string> FANS = ["user1","user2"];
const list<string> NAMES_LIST = ["Tom", "Jack"];


# 第一个示例以 thrift 数据类型使用,属性之间 使用分号，或者逗号，或者不写，都是可以的
struct Person{
    1: required i8 id;
    2:optional string name;
    4:optional i16 age;
    # thrift 没有日期数据类型，自定义一个日期数据类型
    5:optional Date birth_day;
    6:optional bool is_delete;
    7:optional list<string> car,
    8:optional set<long> phone_number
}

/*
* 使用 Java 数据类型风格进行定义 数据结构
**/
struct Car{
    1:optional int id;
    2:optional String brand;
    3:optional String color;
    4:optional Boolean is_delete;
}

/*
* 自定义的异常类
**/
exception GlobeException{
    1:optional String message;
    2:optional int code;
    # thrift 并不支持时间类型(Java中起始页是于对象的形式的)
    3:optional String DateTime;
}

struct Date{
    1:required int year;
    2:required short month;
    3:required short day;

}

/*
 * 自定义日期时间类型
 */
struct DateTime{
    1:required int year;
    2:required short month;
    3:required short day;
    4:required i8 hour;
    5:required short minute;
    6:required short second;
}

service PersonService{

    // 定义方法,有返回值
    Person getById(1:required i8 id)

    // 定义一个方法，没有返回值，有抛出异常
    void save(1:required Person person) throws (1:GlobeException e)

}

/*
 * 枚举类，若枚举类有赋值的话，生成的枚举中会生成一个相应类型的属性并全部赋值（请结合生成的文件解读）
 */
enum Week{
    Monday = 1,
    Tuesday= 2,
    Wednesday
    Thursday
    Friday
    Saturday;
    Sunday
}


# 生成文件，生成的文件和 GoogleProtoBuff 不同，thrift 每一个类生成一个文件，protobuf 则是只生成一个文件
# 生成 语言的命令 thrift --gen java source.thrift -->>>> 指定所要生成的语言类,指定thrift的文件源
# 如：thrift --gen java apache-thrift/src/thrift/ThriftData.thrift -->>> 注意，生成的文件在执行命令的路径处