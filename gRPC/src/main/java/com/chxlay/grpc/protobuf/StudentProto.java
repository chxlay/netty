// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Student.proto

package com.chxlay.grpc.protobuf;

public final class StudentProto {
    private StudentProto() {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions(
                (com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor
            internal_static_com_chxlay_proto_ProtoRequest_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_com_chxlay_proto_ProtoRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor
            internal_static_com_chxlay_proto_ProtoResponse_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_com_chxlay_proto_ProtoResponse_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor
            internal_static_com_chxlay_proto_ResponseList_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_com_chxlay_proto_ResponseList_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor
            internal_static_com_chxlay_proto_StreamRequest_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_com_chxlay_proto_StreamRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor
            internal_static_com_chxlay_proto_StreamResponse_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_com_chxlay_proto_StreamResponse_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor
            internal_static_com_chxlay_proto_User_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_com_chxlay_proto_User_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        java.lang.String[] descriptorData = {
                "\n\rStudent.proto\022\020com.chxlay.proto\"C\n\014Pro" +
                        "toRequest\022\016\n\006stu_id\030\001 \001(\005\022\020\n\010stu_name\030\002 " +
                        "\001(\t\022\021\n\tstu_clazz\030\003 \001(\t\"\200\001\n\rProtoResponse" +
                        "\022\n\n\002id\030\001 \001(\t\022\022\n\nfirst_name\030\002 \001(\t\022\021\n\tlast" +
                        "_name\030\003 \001(\t\022\013\n\003age\030\004 \001(\005\022\016\n\006height\030\005 \001(\005" +
                        "\022\016\n\006weight\030\006 \001(\005\022\017\n\007address\030\007 \001(\t\"A\n\014Res" +
                        "ponseList\0221\n\010response\030\001 \003(\0132\037.com.chxlay" +
                        ".proto.ProtoResponse\"5\n\rStreamRequest\022$\n" +
                        "\004user\030\001 \001(\0132\026.com.chxlay.proto.User\"6\n\016S" +
                        "treamResponse\022$\n\004user\030\001 \001(\0132\026.com.chxlay" +
                        ".proto.User\"8\n\004User\022\n\n\002id\030\001 \001(\005\022\021\n\tuser_" +
                        "name\030\002 \001(\t\022\021\n\tnick_name\030\003 \001(\t2\361\002\n\016Studen" +
                        "tService\022N\n\tReqGetRes\022\036.com.chxlay.proto" +
                        ".ProtoRequest\032\037.com.chxlay.proto.ProtoRe" +
                        "sponse\"\000\022V\n\017ReqGetStreamRes\022\036.com.chxlay" +
                        ".proto.ProtoRequest\032\037.com.chxlay.proto.P" +
                        "rotoResponse\"\0000\001\022U\n\017StreamReqGetRes\022\036.co" +
                        "m.chxlay.proto.ProtoRequest\032\036.com.chxlay" +
                        ".proto.ResponseList\"\000(\001\022`\n\025StreamReqGetS" +
                        "treamRes\022\037.com.chxlay.proto.StreamReques" +
                        "t\032 .com.chxlay.proto.StreamResponse\"\000(\0010" +
                        "\001B,\n\030com.chxlay.grpc.protobufB\014StudentPr" +
                        "otoH\001P\001b\006proto3"
        };
        descriptor = com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[]{
                        });
        internal_static_com_chxlay_proto_ProtoRequest_descriptor =
                getDescriptor().getMessageTypes().get(0);
        internal_static_com_chxlay_proto_ProtoRequest_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_chxlay_proto_ProtoRequest_descriptor,
                new java.lang.String[]{"StuId", "StuName", "StuClazz",});
        internal_static_com_chxlay_proto_ProtoResponse_descriptor =
                getDescriptor().getMessageTypes().get(1);
        internal_static_com_chxlay_proto_ProtoResponse_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_chxlay_proto_ProtoResponse_descriptor,
                new java.lang.String[]{"Id", "FirstName", "LastName", "Age", "Height", "Weight", "Address",});
        internal_static_com_chxlay_proto_ResponseList_descriptor =
                getDescriptor().getMessageTypes().get(2);
        internal_static_com_chxlay_proto_ResponseList_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_chxlay_proto_ResponseList_descriptor,
                new java.lang.String[]{"Response",});
        internal_static_com_chxlay_proto_StreamRequest_descriptor =
                getDescriptor().getMessageTypes().get(3);
        internal_static_com_chxlay_proto_StreamRequest_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_chxlay_proto_StreamRequest_descriptor,
                new java.lang.String[]{"User",});
        internal_static_com_chxlay_proto_StreamResponse_descriptor =
                getDescriptor().getMessageTypes().get(4);
        internal_static_com_chxlay_proto_StreamResponse_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_chxlay_proto_StreamResponse_descriptor,
                new java.lang.String[]{"User",});
        internal_static_com_chxlay_proto_User_descriptor =
                getDescriptor().getMessageTypes().get(5);
        internal_static_com_chxlay_proto_User_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_chxlay_proto_User_descriptor,
                new java.lang.String[]{"Id", "UserName", "NickName",});
    }

    // @@protoc_insertion_point(outer_class_scope)
}
