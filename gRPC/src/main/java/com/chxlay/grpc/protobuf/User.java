// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Student.proto

package com.chxlay.grpc.protobuf;

/**
 * <pre>
 * 流式传输数据体内容示例
 * </pre>
 * <p>
 * Protobuf type {@code com.chxlay.proto.User}
 */
public final class User extends
        com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:com.chxlay.proto.User)
        UserOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use User.newBuilder() to construct.
    private User(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private User() {
        userName_ = "";
        nickName_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
            UnusedPrivateParameter unused) {
        return new User();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
        return this.unknownFields;
    }

    private User(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new java.lang.NullPointerException();
        }
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                com.google.protobuf.UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            while (!done) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        done = true;
                        break;
                    case 8: {

                        id_ = input.readInt32();
                        break;
                    }
                    case 18: {
                        java.lang.String s = input.readStringRequireUtf8();

                        userName_ = s;
                        break;
                    }
                    case 26: {
                        java.lang.String s = input.readStringRequireUtf8();

                        nickName_ = s;
                        break;
                    }
                    default: {
                        if (!parseUnknownField(
                                input, unknownFields, extensionRegistry, tag)) {
                            done = true;
                        }
                        break;
                    }
                }
            }
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(this);
        } catch (java.io.IOException e) {
            throw new com.google.protobuf.InvalidProtocolBufferException(
                    e).setUnfinishedMessage(this);
        } finally {
            this.unknownFields = unknownFields.build();
            makeExtensionsImmutable();
        }
    }

    public static final com.google.protobuf.Descriptors.Descriptor
    getDescriptor() {
        return com.chxlay.grpc.protobuf.StudentProto.internal_static_com_chxlay_proto_User_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
    internalGetFieldAccessorTable() {
        return com.chxlay.grpc.protobuf.StudentProto.internal_static_com_chxlay_proto_User_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        com.chxlay.grpc.protobuf.User.class, com.chxlay.grpc.protobuf.User.Builder.class);
    }

    public static final int ID_FIELD_NUMBER = 1;
    private int id_;

    /**
     * <code>int32 id = 1;</code>
     *
     * @return The id.
     */
    @java.lang.Override
    public int getId() {
        return id_;
    }

    public static final int USER_NAME_FIELD_NUMBER = 2;
    private volatile java.lang.Object userName_;

    /**
     * <code>string user_name = 2;</code>
     *
     * @return The userName.
     */
    @java.lang.Override
    public java.lang.String getUserName() {
        java.lang.Object ref = userName_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs =
                    (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            userName_ = s;
            return s;
        }
    }

    /**
     * <code>string user_name = 2;</code>
     *
     * @return The bytes for userName.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
    getUserNameBytes() {
        java.lang.Object ref = userName_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8(
                            (java.lang.String) ref);
            userName_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int NICK_NAME_FIELD_NUMBER = 3;
    private volatile java.lang.Object nickName_;

    /**
     * <code>string nick_name = 3;</code>
     *
     * @return The nickName.
     */
    @java.lang.Override
    public java.lang.String getNickName() {
        java.lang.Object ref = nickName_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs =
                    (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            nickName_ = s;
            return s;
        }
    }

    /**
     * <code>string nick_name = 3;</code>
     *
     * @return The bytes for nickName.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
    getNickNameBytes() {
        java.lang.Object ref = nickName_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8(
                            (java.lang.String) ref);
            nickName_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    private byte memoizedIsInitialized = -1;

    @java.lang.Override
    public final boolean isInitialized() {
        byte isInitialized = memoizedIsInitialized;
        if (isInitialized == 1) return true;
        if (isInitialized == 0) return false;

        memoizedIsInitialized = 1;
        return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
            throws java.io.IOException {
        if (id_ != 0) {
            output.writeInt32(1, id_);
        }
        if (!getUserNameBytes().isEmpty()) {
            com.google.protobuf.GeneratedMessageV3.writeString(output, 2, userName_);
        }
        if (!getNickNameBytes().isEmpty()) {
            com.google.protobuf.GeneratedMessageV3.writeString(output, 3, nickName_);
        }
        unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
        int size = memoizedSize;
        if (size != -1) return size;

        size = 0;
        if (id_ != 0) {
            size += com.google.protobuf.CodedOutputStream
                    .computeInt32Size(1, id_);
        }
        if (!getUserNameBytes().isEmpty()) {
            size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, userName_);
        }
        if (!getNickNameBytes().isEmpty()) {
            size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, nickName_);
        }
        size += unknownFields.getSerializedSize();
        memoizedSize = size;
        return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof com.chxlay.grpc.protobuf.User)) {
            return super.equals(obj);
        }
        com.chxlay.grpc.protobuf.User other = (com.chxlay.grpc.protobuf.User) obj;

        if (getId()
                != other.getId()) return false;
        if (!getUserName()
                .equals(other.getUserName())) return false;
        if (!getNickName()
                .equals(other.getNickName())) return false;
        if (!unknownFields.equals(other.unknownFields)) return false;
        return true;
    }

    @java.lang.Override
    public int hashCode() {
        if (memoizedHashCode != 0) {
            return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptor().hashCode();
        hash = (37 * hash) + ID_FIELD_NUMBER;
        hash = (53 * hash) + getId();
        hash = (37 * hash) + USER_NAME_FIELD_NUMBER;
        hash = (53 * hash) + getUserName().hashCode();
        hash = (37 * hash) + NICK_NAME_FIELD_NUMBER;
        hash = (53 * hash) + getNickName().hashCode();
        hash = (29 * hash) + unknownFields.hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static com.chxlay.grpc.protobuf.User parseFrom(
            java.nio.ByteBuffer data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static com.chxlay.grpc.protobuf.User parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static com.chxlay.grpc.protobuf.User parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static com.chxlay.grpc.protobuf.User parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static com.chxlay.grpc.protobuf.User parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static com.chxlay.grpc.protobuf.User parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static com.chxlay.grpc.protobuf.User parseFrom(java.io.InputStream input)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input);
    }

    public static com.chxlay.grpc.protobuf.User parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static com.chxlay.grpc.protobuf.User parseDelimitedFrom(java.io.InputStream input)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input);
    }

    public static com.chxlay.grpc.protobuf.User parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static com.chxlay.grpc.protobuf.User parseFrom(
            com.google.protobuf.CodedInputStream input)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input);
    }

    public static com.chxlay.grpc.protobuf.User parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(com.chxlay.grpc.protobuf.User prototype) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }

    @java.lang.Override
    public Builder toBuilder() {
        return this == DEFAULT_INSTANCE
                ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
            com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
    }

    /**
     * <pre>
     * 流式传输数据体内容示例
     * </pre>
     * <p>
     * Protobuf type {@code com.chxlay.proto.User}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:com.chxlay.proto.User)
            com.chxlay.grpc.protobuf.UserOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return com.chxlay.grpc.protobuf.StudentProto.internal_static_com_chxlay_proto_User_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return com.chxlay.grpc.protobuf.StudentProto.internal_static_com_chxlay_proto_User_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            com.chxlay.grpc.protobuf.User.class, com.chxlay.grpc.protobuf.User.Builder.class);
        }

        // Construct using com.chxlay.grpc.protobuf.User.newBuilder()
        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(
                com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (com.google.protobuf.GeneratedMessageV3
                    .alwaysUseFieldBuilders) {
            }
        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            id_ = 0;

            userName_ = "";

            nickName_ = "";

            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
            return com.chxlay.grpc.protobuf.StudentProto.internal_static_com_chxlay_proto_User_descriptor;
        }

        @java.lang.Override
        public com.chxlay.grpc.protobuf.User getDefaultInstanceForType() {
            return com.chxlay.grpc.protobuf.User.getDefaultInstance();
        }

        @java.lang.Override
        public com.chxlay.grpc.protobuf.User build() {
            com.chxlay.grpc.protobuf.User result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public com.chxlay.grpc.protobuf.User buildPartial() {
            com.chxlay.grpc.protobuf.User result = new com.chxlay.grpc.protobuf.User(this);
            result.id_ = id_;
            result.userName_ = userName_;
            result.nickName_ = nickName_;
            onBuilt();
            return result;
        }

        @java.lang.Override
        public Builder clone() {
            return super.clone();
        }

        @java.lang.Override
        public Builder setField(
                com.google.protobuf.Descriptors.FieldDescriptor field,
                java.lang.Object value) {
            return super.setField(field, value);
        }

        @java.lang.Override
        public Builder clearField(
                com.google.protobuf.Descriptors.FieldDescriptor field) {
            return super.clearField(field);
        }

        @java.lang.Override
        public Builder clearOneof(
                com.google.protobuf.Descriptors.OneofDescriptor oneof) {
            return super.clearOneof(oneof);
        }

        @java.lang.Override
        public Builder setRepeatedField(
                com.google.protobuf.Descriptors.FieldDescriptor field,
                int index, java.lang.Object value) {
            return super.setRepeatedField(field, index, value);
        }

        @java.lang.Override
        public Builder addRepeatedField(
                com.google.protobuf.Descriptors.FieldDescriptor field,
                java.lang.Object value) {
            return super.addRepeatedField(field, value);
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof com.chxlay.grpc.protobuf.User) {
                return mergeFrom((com.chxlay.grpc.protobuf.User) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(com.chxlay.grpc.protobuf.User other) {
            if (other == com.chxlay.grpc.protobuf.User.getDefaultInstance()) return this;
            if (other.getId() != 0) {
                setId(other.getId());
            }
            if (!other.getUserName().isEmpty()) {
                userName_ = other.userName_;
                onChanged();
            }
            if (!other.getNickName().isEmpty()) {
                nickName_ = other.nickName_;
                onChanged();
            }
            this.mergeUnknownFields(other.unknownFields);
            onChanged();
            return this;
        }

        @java.lang.Override
        public final boolean isInitialized() {
            return true;
        }

        @java.lang.Override
        public Builder mergeFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            com.chxlay.grpc.protobuf.User parsedMessage = null;
            try {
                parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                parsedMessage = (com.chxlay.grpc.protobuf.User) e.getUnfinishedMessage();
                throw e.unwrapIOException();
            } finally {
                if (parsedMessage != null) {
                    mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        private int id_;

        /**
         * <code>int32 id = 1;</code>
         *
         * @return The id.
         */
        @java.lang.Override
        public int getId() {
            return id_;
        }

        /**
         * <code>int32 id = 1;</code>
         *
         * @param value The id to set.
         * @return This builder for chaining.
         */
        public Builder setId(int value) {

            id_ = value;
            onChanged();
            return this;
        }

        /**
         * <code>int32 id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearId() {

            id_ = 0;
            onChanged();
            return this;
        }

        private java.lang.Object userName_ = "";

        /**
         * <code>string user_name = 2;</code>
         *
         * @return The userName.
         */
        @Override
        public java.lang.String getUserName() {
            java.lang.Object ref = userName_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                userName_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string user_name = 2;</code>
         *
         * @return The bytes for userName.
         */
        @Override
        public com.google.protobuf.ByteString
        getUserNameBytes() {
            java.lang.Object ref = userName_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                userName_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string user_name = 2;</code>
         *
         * @param value The userName to set.
         * @return This builder for chaining.
         */
        public Builder setUserName(
                java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }

            userName_ = value;
            onChanged();
            return this;
        }

        /**
         * <code>string user_name = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUserName() {

            userName_ = getDefaultInstance().getUserName();
            onChanged();
            return this;
        }

        /**
         * <code>string user_name = 2;</code>
         *
         * @param value The bytes for userName to set.
         * @return This builder for chaining.
         */
        public Builder setUserNameBytes(
                com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);

            userName_ = value;
            onChanged();
            return this;
        }

        private java.lang.Object nickName_ = "";

        /**
         * <code>string nick_name = 3;</code>
         *
         * @return The nickName.
         */
        @Override
        public java.lang.String getNickName() {
            java.lang.Object ref = nickName_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                nickName_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string nick_name = 3;</code>
         *
         * @return The bytes for nickName.
         */
        @Override
        public com.google.protobuf.ByteString
        getNickNameBytes() {
            java.lang.Object ref = nickName_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                nickName_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string nick_name = 3;</code>
         *
         * @param value The nickName to set.
         * @return This builder for chaining.
         */
        public Builder setNickName(
                java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }

            nickName_ = value;
            onChanged();
            return this;
        }

        /**
         * <code>string nick_name = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearNickName() {

            nickName_ = getDefaultInstance().getNickName();
            onChanged();
            return this;
        }

        /**
         * <code>string nick_name = 3;</code>
         *
         * @param value The bytes for nickName to set.
         * @return This builder for chaining.
         */
        public Builder setNickNameBytes(
                com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);

            nickName_ = value;
            onChanged();
            return this;
        }

        @java.lang.Override
        public final Builder setUnknownFields(
                final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.setUnknownFields(unknownFields);
        }

        @java.lang.Override
        public final Builder mergeUnknownFields(
                final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.mergeUnknownFields(unknownFields);
        }


        // @@protoc_insertion_point(builder_scope:com.chxlay.proto.User)
    }

    // @@protoc_insertion_point(class_scope:com.chxlay.proto.User)
    private static final com.chxlay.grpc.protobuf.User DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE = new com.chxlay.grpc.protobuf.User();
    }

    public static com.chxlay.grpc.protobuf.User getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<User>
            PARSER = new com.google.protobuf.AbstractParser<User>() {
        @java.lang.Override
        public User parsePartialFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return new User(input, extensionRegistry);
        }
    };

    public static com.google.protobuf.Parser<User> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<User> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public com.chxlay.grpc.protobuf.User getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}

