/**
 * Autogenerated by Thrift Compiler (0.13.0)
 * <p>
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *
 * @generated
 */
package com.chxlay.thrift.generate;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.13.0)", date = "2020-12-06")
public class Date implements org.apache.thrift.TBase<Date, Date._Fields>, java.io.Serializable, Cloneable, Comparable<Date> {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Date");

    private static final org.apache.thrift.protocol.TField YEAR_FIELD_DESC = new org.apache.thrift.protocol.TField("year", org.apache.thrift.protocol.TType.I32, (short) 1);
    private static final org.apache.thrift.protocol.TField MONTH_FIELD_DESC = new org.apache.thrift.protocol.TField("month", org.apache.thrift.protocol.TType.I16, (short) 2);
    private static final org.apache.thrift.protocol.TField DAY_FIELD_DESC = new org.apache.thrift.protocol.TField("day", org.apache.thrift.protocol.TType.I16, (short) 3);

    private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new DateStandardSchemeFactory();
    private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new DateTupleSchemeFactory();

    public int year; // required
    public short month; // required
    public short day; // required

    /**
     * The set of fields this struct contains, along with convenience methods for finding and manipulating them.
     */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
        YEAR((short) 1, "year"),
        MONTH((short) 2, "month"),
        DAY((short) 3, "day");

        private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

        static {
            for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
                byName.put(field.getFieldName(), field);
            }
        }

        /**
         * Find the _Fields constant that matches fieldId, or null if its not found.
         */
        @org.apache.thrift.annotation.Nullable
        public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
                case 1: // YEAR
                    return YEAR;
                case 2: // MONTH
                    return MONTH;
                case 3: // DAY
                    return DAY;
                default:
                    return null;
            }
        }

        /**
         * Find the _Fields constant that matches fieldId, throwing an exception
         * if it is not found.
         */
        public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            return fields;
        }

        /**
         * Find the _Fields constant that matches name, or null if its not found.
         */
        @org.apache.thrift.annotation.Nullable
        public static _Fields findByName(java.lang.String name) {
            return byName.get(name);
        }

        private final short _thriftId;
        private final java.lang.String _fieldName;

        _Fields(short thriftId, java.lang.String fieldName) {
            _thriftId = thriftId;
            _fieldName = fieldName;
        }

        public short getThriftFieldId() {
            return _thriftId;
        }

        public java.lang.String getFieldName() {
            return _fieldName;
        }
    }

    // isset id assignments
    private static final int __YEAR_ISSET_ID = 0;
    private static final int __MONTH_ISSET_ID = 1;
    private static final int __DAY_ISSET_ID = 2;
    private byte __isset_bitfield = 0;
    public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;

    static {
        java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
        tmpMap.put(_Fields.YEAR, new org.apache.thrift.meta_data.FieldMetaData("year", org.apache.thrift.TFieldRequirementType.REQUIRED,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32, "int")));
        tmpMap.put(_Fields.MONTH, new org.apache.thrift.meta_data.FieldMetaData("month", org.apache.thrift.TFieldRequirementType.REQUIRED,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I16, "short")));
        tmpMap.put(_Fields.DAY, new org.apache.thrift.meta_data.FieldMetaData("day", org.apache.thrift.TFieldRequirementType.REQUIRED,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I16, "short")));
        metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
        org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Date.class, metaDataMap);
    }

    public Date() {
    }

    public Date(
            int year,
            short month,
            short day) {
        this();
        this.year = year;
        setYearIsSet(true);
        this.month = month;
        setMonthIsSet(true);
        this.day = day;
        setDayIsSet(true);
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public Date(Date other) {
        __isset_bitfield = other.__isset_bitfield;
        this.year = other.year;
        this.month = other.month;
        this.day = other.day;
    }

    public Date deepCopy() {
        return new Date(this);
    }

    @Override
    public void clear() {
        setYearIsSet(false);
        this.year = 0;
        setMonthIsSet(false);
        this.month = 0;
        setDayIsSet(false);
        this.day = 0;
    }

    public int getYear() {
        return this.year;
    }

    public Date setYear(int year) {
        this.year = year;
        setYearIsSet(true);
        return this;
    }

    public void unsetYear() {
        __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __YEAR_ISSET_ID);
    }

    /**
     * Returns true if field year is set (has been assigned a value) and false otherwise
     */
    public boolean isSetYear() {
        return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __YEAR_ISSET_ID);
    }

    public void setYearIsSet(boolean value) {
        __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __YEAR_ISSET_ID, value);
    }

    public short getMonth() {
        return this.month;
    }

    public Date setMonth(short month) {
        this.month = month;
        setMonthIsSet(true);
        return this;
    }

    public void unsetMonth() {
        __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __MONTH_ISSET_ID);
    }

    /**
     * Returns true if field month is set (has been assigned a value) and false otherwise
     */
    public boolean isSetMonth() {
        return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __MONTH_ISSET_ID);
    }

    public void setMonthIsSet(boolean value) {
        __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __MONTH_ISSET_ID, value);
    }

    public short getDay() {
        return this.day;
    }

    public Date setDay(short day) {
        this.day = day;
        setDayIsSet(true);
        return this;
    }

    public void unsetDay() {
        __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __DAY_ISSET_ID);
    }

    /**
     * Returns true if field day is set (has been assigned a value) and false otherwise
     */
    public boolean isSetDay() {
        return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __DAY_ISSET_ID);
    }

    public void setDayIsSet(boolean value) {
        __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __DAY_ISSET_ID, value);
    }

    public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
        switch (field) {
            case YEAR:
                if (value == null) {
                    unsetYear();
                } else {
                    setYear((java.lang.Integer) value);
                }
                break;

            case MONTH:
                if (value == null) {
                    unsetMonth();
                } else {
                    setMonth((java.lang.Short) value);
                }
                break;

            case DAY:
                if (value == null) {
                    unsetDay();
                } else {
                    setDay((java.lang.Short) value);
                }
                break;

        }
    }

    @org.apache.thrift.annotation.Nullable
    public java.lang.Object getFieldValue(_Fields field) {
        switch (field) {
            case YEAR:
                return getYear();

            case MONTH:
                return getMonth();

            case DAY:
                return getDay();

        }
        throw new java.lang.IllegalStateException();
    }

    /**
     * Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise
     */
    public boolean isSet(_Fields field) {
        if (field == null) {
            throw new java.lang.IllegalArgumentException();
        }

        switch (field) {
            case YEAR:
                return isSetYear();
            case MONTH:
                return isSetMonth();
            case DAY:
                return isSetDay();
        }
        throw new java.lang.IllegalStateException();
    }

    @Override
    public boolean equals(java.lang.Object that) {
        if (that == null)
            return false;
        if (that instanceof Date)
            return this.equals((Date) that);
        return false;
    }

    public boolean equals(Date that) {
        if (that == null)
            return false;
        if (this == that)
            return true;

        boolean this_present_year = true;
        boolean that_present_year = true;
        if (this_present_year || that_present_year) {
            if (!(this_present_year && that_present_year))
                return false;
            if (this.year != that.year)
                return false;
        }

        boolean this_present_month = true;
        boolean that_present_month = true;
        if (this_present_month || that_present_month) {
            if (!(this_present_month && that_present_month))
                return false;
            if (this.month != that.month)
                return false;
        }

        boolean this_present_day = true;
        boolean that_present_day = true;
        if (this_present_day || that_present_day) {
            if (!(this_present_day && that_present_day))
                return false;
            if (this.day != that.day)
                return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;

        hashCode = hashCode * 8191 + year;

        hashCode = hashCode * 8191 + month;

        hashCode = hashCode * 8191 + day;

        return hashCode;
    }

    @Override
    public int compareTo(Date other) {
        if (!getClass().equals(other.getClass())) {
            return getClass().getName().compareTo(other.getClass().getName());
        }

        int lastComparison = 0;

        lastComparison = java.lang.Boolean.valueOf(isSetYear()).compareTo(other.isSetYear());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetYear()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.year, other.year);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = java.lang.Boolean.valueOf(isSetMonth()).compareTo(other.isSetMonth());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetMonth()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.month, other.month);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = java.lang.Boolean.valueOf(isSetDay()).compareTo(other.isSetDay());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetDay()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.day, other.day);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        return 0;
    }

    @org.apache.thrift.annotation.Nullable
    public _Fields fieldForId(int fieldId) {
        return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
        scheme(iprot).read(iprot, this);
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
        scheme(oprot).write(oprot, this);
    }

    @Override
    public java.lang.String toString() {
        java.lang.StringBuilder sb = new java.lang.StringBuilder("Date(");
        boolean first = true;

        sb.append("year:");
        sb.append(this.year);
        first = false;
        if (!first) sb.append(", ");
        sb.append("month:");
        sb.append(this.month);
        first = false;
        if (!first) sb.append(", ");
        sb.append("day:");
        sb.append(this.day);
        first = false;
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
        // check for required fields
        // alas, we cannot check 'year' because it's a primitive and you chose the non-beans generator.
        // alas, we cannot check 'month' because it's a primitive and you chose the non-beans generator.
        // alas, we cannot check 'day' because it's a primitive and you chose the non-beans generator.
        // check for sub-struct validity
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
        try {
            write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
        } catch (org.apache.thrift.TException te) {
            throw new java.io.IOException(te);
        }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
        try {
            // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
            __isset_bitfield = 0;
            read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
        } catch (org.apache.thrift.TException te) {
            throw new java.io.IOException(te);
        }
    }

    private static class DateStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
        public DateStandardScheme getScheme() {
            return new DateStandardScheme();
        }
    }

    private static class DateStandardScheme extends org.apache.thrift.scheme.StandardScheme<Date> {

        public void read(org.apache.thrift.protocol.TProtocol iprot, Date struct) throws org.apache.thrift.TException {
            org.apache.thrift.protocol.TField schemeField;
            iprot.readStructBegin();
            while (true) {
                schemeField = iprot.readFieldBegin();
                if (schemeField.type == org.apache.thrift.protocol.TType.STOP) {
                    break;
                }
                switch (schemeField.id) {
                    case 1: // YEAR
                        if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
                            struct.year = iprot.readI32();
                            struct.setYearIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    case 2: // MONTH
                        if (schemeField.type == org.apache.thrift.protocol.TType.I16) {
                            struct.month = iprot.readI16();
                            struct.setMonthIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    case 3: // DAY
                        if (schemeField.type == org.apache.thrift.protocol.TType.I16) {
                            struct.day = iprot.readI16();
                            struct.setDayIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    default:
                        org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                }
                iprot.readFieldEnd();
            }
            iprot.readStructEnd();

            // check for required fields of primitive type, which can't be checked in the validate method
            if (!struct.isSetYear()) {
                throw new org.apache.thrift.protocol.TProtocolException("Required field 'year' was not found in serialized data! Struct: " + toString());
            }
            if (!struct.isSetMonth()) {
                throw new org.apache.thrift.protocol.TProtocolException("Required field 'month' was not found in serialized data! Struct: " + toString());
            }
            if (!struct.isSetDay()) {
                throw new org.apache.thrift.protocol.TProtocolException("Required field 'day' was not found in serialized data! Struct: " + toString());
            }
            struct.validate();
        }

        public void write(org.apache.thrift.protocol.TProtocol oprot, Date struct) throws org.apache.thrift.TException {
            struct.validate();

            oprot.writeStructBegin(STRUCT_DESC);
            oprot.writeFieldBegin(YEAR_FIELD_DESC);
            oprot.writeI32(struct.year);
            oprot.writeFieldEnd();
            oprot.writeFieldBegin(MONTH_FIELD_DESC);
            oprot.writeI16(struct.month);
            oprot.writeFieldEnd();
            oprot.writeFieldBegin(DAY_FIELD_DESC);
            oprot.writeI16(struct.day);
            oprot.writeFieldEnd();
            oprot.writeFieldStop();
            oprot.writeStructEnd();
        }

    }

    private static class DateTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
        public DateTupleScheme getScheme() {
            return new DateTupleScheme();
        }
    }

    private static class DateTupleScheme extends org.apache.thrift.scheme.TupleScheme<Date> {

        @Override
        public void write(org.apache.thrift.protocol.TProtocol prot, Date struct) throws org.apache.thrift.TException {
            org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
            oprot.writeI32(struct.year);
            oprot.writeI16(struct.month);
            oprot.writeI16(struct.day);
        }

        @Override
        public void read(org.apache.thrift.protocol.TProtocol prot, Date struct) throws org.apache.thrift.TException {
            org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
            struct.year = iprot.readI32();
            struct.setYearIsSet(true);
            struct.month = iprot.readI16();
            struct.setMonthIsSet(true);
            struct.day = iprot.readI16();
            struct.setDayIsSet(true);
        }
    }

    private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
        return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
    }
}

