function generatorColumn(fieldId, fieldName, editAble) {
    if (editAble) {
        return {
            field: fieldId,
            title: fieldName,
            editable: {
                type: 'text',
                title: fieldName
            }
        }
    } else {
        return {
            field: fieldId,
            title: fieldName
        }
    }
}