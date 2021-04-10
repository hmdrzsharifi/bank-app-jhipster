import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IOffice, defaultValue } from 'app/shared/model/office.model';

export const ACTION_TYPES = {
  FETCH_OFFICE_LIST: 'office/FETCH_OFFICE_LIST',
  FETCH_OFFICE: 'office/FETCH_OFFICE',
  CREATE_OFFICE: 'office/CREATE_OFFICE',
  UPDATE_OFFICE: 'office/UPDATE_OFFICE',
  DELETE_OFFICE: 'office/DELETE_OFFICE',
  RESET: 'office/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IOffice>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type OfficeState = Readonly<typeof initialState>;

// Reducer

export default (state: OfficeState = initialState, action): OfficeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_OFFICE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_OFFICE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_OFFICE):
    case REQUEST(ACTION_TYPES.UPDATE_OFFICE):
    case REQUEST(ACTION_TYPES.DELETE_OFFICE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_OFFICE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_OFFICE):
    case FAILURE(ACTION_TYPES.CREATE_OFFICE):
    case FAILURE(ACTION_TYPES.UPDATE_OFFICE):
    case FAILURE(ACTION_TYPES.DELETE_OFFICE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_OFFICE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_OFFICE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_OFFICE):
    case SUCCESS(ACTION_TYPES.UPDATE_OFFICE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_OFFICE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/offices';

// Actions

export const getEntities: ICrudGetAllAction<IOffice> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_OFFICE_LIST,
  payload: axios.get<IOffice>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IOffice> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_OFFICE,
    payload: axios.get<IOffice>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IOffice> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_OFFICE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IOffice> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_OFFICE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IOffice> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_OFFICE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
