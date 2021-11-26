<template>
  <div>
    <entity-header />
    <entity-table :entity-embedded-info="entities" :column-info="columnInfo" />
    <entity-pagination />
  </div>
</template>

<script>
import EntityTable from '@/components/entity/EntityTable'
import EntityHeader from '@/components/entity/EntityHeader'
import EntityPagination from '@/components/entity/EntityPagination'
import { getEntities, getProfile } from '@/api/entityApi'
export default {
  name: 'Entity',
  components: { EntityHeader, EntityTable, EntityPagination },
  props: {
    entityName: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      msg: 'Entity',
      columnInfo: [],
      entities: []
    }
  },
  watch: {
    entities: {
      deep: true,
      handler(newVal, oldVal) {
        this.tableData = newVal
      }
    },
    columnInfo: {
      deep: true,
      handler(newVal, oldVal) {
        this.columnInfo = newVal
      }
    }
  },
  created() {
    getEntities(this.entityName).then(response => {
      this.entities = response.data['_embedded'][this.entityName]
    }).catch(error => {
      console.warn(error)
    })
    getProfile(this.entityName).then(response => {
      const description = response.data.alps['descriptor'][0]['descriptor']
      this.columnInfo = description
    }).catch(error => {
      console.log(error)
    })
  }
}
</script>

<style>

</style>
